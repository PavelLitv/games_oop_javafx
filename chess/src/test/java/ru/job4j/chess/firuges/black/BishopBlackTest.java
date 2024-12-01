package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BishopBlackTest {
    @Test
    void whenPositionThenCurrentPosition() {
        Cell expectedPosition = Cell.A3;
        Figure figure = new BishopBlack(expectedPosition);
        Cell actualPosition = figure.position();
        assertThat(actualPosition).isEqualTo(expectedPosition);
    }

    @Test
    void whenCopyThenGetCopy() {
        Cell position = Cell.A3;
        Figure figure = new BishopBlack(position);
        Figure copy = figure.copy(position);
        assertThat(copy.position()).isEqualTo(position);
    }

    @Test
    void whenWayC1ToG5ThenReturnD2E3F4G5() {
        Cell[] expected = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        Cell position = Cell.C1;
        Figure figure = new BishopBlack(position);
        Cell[] way = figure.way(Cell.G5);
        assertThat(way).isEqualTo(expected);
    }

    @Test
    void whenIsDiagonalC1ToG6ThenException() {
        Cell position = Cell.C1;
        Figure figure = new BishopBlack(position);
        assertThatThrownBy(() -> figure.way(Cell.G6))
                .isInstanceOf(ImpossibleMoveException.class)
                .hasMessage("Could not move by diagonal from C1 to G6");
    }
}