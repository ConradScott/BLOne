package uk.me.conradscott.blone.hof;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class HOFsTest {
    @Test public void testFoldlInteger() {
        final Integer sum = HOFs.foldl( IntStream.range( 0, 10 ).iterator(), 0, ( o, o2 ) -> o + o2 );

        assertEquals( "Sum of [0, 10) should be 45.", sum, Integer.valueOf( 45 ) );
    }

    @Test public void testFoldlDouble() {
        final Double sum = HOFs.foldl( IntStream.range( 0, 10 ).iterator(), 0.0, ( o, o2 ) -> o + o2 );

        assertEquals( "Sum of [0, 10) should be 45.", sum, Double.valueOf( 45.0 ) );
    }
}
