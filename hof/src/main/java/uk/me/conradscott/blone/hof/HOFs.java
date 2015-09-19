package uk.me.conradscott.blone.hof;

import java.util.Iterator;
import java.util.function.BiFunction;

public final class HOFs {
    private HOFs() {}

    public static < T, R > R foldl( final Iterable< T > iterable,
                                    final R initial,
                                    final BiFunction< R, ? super T, R > folder )
    {
        return foldl( iterable.iterator(), initial, folder );
    }

    public static < T, R > R foldl( final Iterator< T > iterator,
                                    final R initial,
                                    final BiFunction< R, ? super T, R > folder )
    {
        R state = initial;

        while ( iterator.hasNext() ) {
            state = folder.apply( state, iterator.next() );
        }

        return state;
    }
}
