import './ChessBoard.css'
import ChessBoardSquare from './ChessBoardSquare.tsx';

const initialBoard = [
    [
        { type: "rook", color: "black" },
        { type: "knight", color: "black" },
        { type: "bishop", color: "black" },
        { type: "queen", color: "black" },
        { type: "king", color: "black" },
        { type: "bishop", color: "black" },
        { type: "knight", color: "black" },
        { type: "rook", color: "black" },
    ],
    Array(8).fill({ type: "pawn", color: "black" }),
    Array(8).fill(null),
    Array(8).fill(null),
    Array(8).fill(null),
    Array(8).fill(null),
    Array(8).fill({ type: "pawn", color: "white" }),
    [
        { type: "rook", color: "white" },
        { type: "knight", color: "white" },
        { type: "bishop", color: "white" },
        { type: "queen", color: "white" },
        { type: "king", color: "white" },
        { type: "bishop", color: "white" },
        { type: "knight", color: "white" },
        { type: "rook", color: "white" },
    ],
];

function ChessBoard() {

    return (
            <div className="chess-board">
                {initialBoard.map((row, rowIndex) => (
                <div className="chess-board-row" key={rowIndex}>
                    {row.map((square, squareIndex) => {
                    const isWhiteSquare = (rowIndex + squareIndex) % 2 === 0;

                    return (
                            <ChessBoardSquare
                            key={squareIndex}
                            piece={square?.type}
                            pieceColor={square?.color}
                            squareColor={isWhiteSquare ? "white" : "black"}
                                />
                        );
                    })}
                </div>
                ))}
            </div>
    );
}

export default ChessBoard