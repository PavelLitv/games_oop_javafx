package ru.job4j.chess;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicTest {
    @Test
    public void whenMoveThenFigureNotFoundException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class, () -> {
            logic.move(Cell.C1, Cell.H6);
        });
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    public void whenMoveAndFigureOnWayThenOccupiedCellException() throws OccupiedCellException {
        Logic logic = new Logic();
        Figure figure = new BishopBlack(Cell.C1);
        Figure figureOnWay = new BishopBlack(Cell.F4);
        logic.add(figure);
        logic.add(figureOnWay);
        assertThatThrownBy(() -> logic.move(figure.position(), Cell.G5))
                .isInstanceOf(OccupiedCellException.class)
                .hasMessage("The cell is occupied");
    }
}