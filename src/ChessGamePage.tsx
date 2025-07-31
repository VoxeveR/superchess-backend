import ChessBoard from "./chess/ChessBoard.tsx";
import {useParams} from "react-router-dom";
import {useEffect} from "react";

function ChessGamePage() {
    const {gameCode} = useParams();
    const playerNickname = localStorage.getItem("nickname");
    console.log(gameCode);

    useEffect(() => {

    }, []);

    return (
        <>
            <h1>Super Chess</h1>
            <h2>Your Opponent: </h2>
            <h3>Game ID: {gameCode}</h3>
            <ChessBoard></ChessBoard>

            <h2 style={{marginTop: "50px"}}>{playerNickname}</h2>
        </>
    );
}

export default ChessGamePage;
