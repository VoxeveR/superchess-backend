import './ChessBoardSquare.css'

interface IProps {
    piece?: "pawn" | "knight" | "queen" | "bishop" | "king" | "rook";
    squareColor: "white" | "black";
    pieceColor?: "white" | "black";
}

function ChessBoardSquare({piece, squareColor, pieceColor} : IProps) {
    return (
        <>
            <div className={`square square${squareColor}`}>
                {piece && (
                    <img src={`/assets/pieces/${pieceColor}_${piece}.svg`}
                         alt={`${pieceColor} ${piece}`}
                         className="piece">
                    </img>
                )}
            </div>
        </>
    )
}

export default ChessBoardSquare