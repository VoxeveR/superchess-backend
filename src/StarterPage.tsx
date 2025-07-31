import { useState, useEffect, useRef } from "react";
import { Client } from "@stomp/stompjs";
import { useNavigate } from 'react-router-dom';

function StarterPage() {
    const [nickname, setNickname] = useState("");
    const [players, setPlayers] = useState<string[]>([]);
    const clientRef = useRef<Client | null>(null);
    const [roomCode, setRoomCode] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
        if (roomCode) {
            console.log("Room code updated:", roomCode);
        }
    }, [roomCode]);

    useEffect(() => {
        const client = new Client({
            brokerURL: "ws://localhost:8081/ws",
            reconnectDelay: 5000,
            debug: (str) => console.log(str),
            onDisconnect: () => {
                console.log("Client disconnected");
            },
            onConnect: () => {
                console.log("Connected to WebSocket");

                client.subscribe("/user/game/roomCode", (message) => {
                    const createdRoomCode: string = message.body;
                    setRoomCode(createdRoomCode);
                });

                client.subscribe("/topic/connect", (message) => {
                    const connectedPlayers: string[] = JSON.parse(message.body);
                    setPlayers(connectedPlayers);
                });
            },
        });

        client.activate();
        clientRef.current = client;

        return () => {
            client.deactivate();
        };
    }, []);

    useEffect(() => {
        if (roomCode) {
            console.log("Room code updated:", roomCode);
            navigate(`/game/${roomCode}`);
        }
    }, [roomCode, navigate]);

    function joinGame(destination: string, nickname: string) {
        const client = clientRef.current;

        if (client && client.connected) {
            client.publish({
                destination: destination,
                body: nickname,
            });
            localStorage.setItem("nickname", nickname);
        } else {
            console.warn("Client not connected yet");
        }
    }

    return (
        <>
            <h1>Super Chess</h1>

            <input
                placeholder="Type nickname"
                onChange={(e) => setNickname(e.target.value)}
            />
            <br />
            <button type="button" onClick={() => joinGame("/app/createRoom", nickname)}>
                Create Game
            </button>

            <h2>Connected Players:</h2>
            <ul>
                {players.map((player, index) => (
                    <li key={index}>{player}</li>
                ))}
            </ul>
        </>
    );
}

export default StarterPage;
