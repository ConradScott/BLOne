package uk.me.conradscott;

/**
 * Does Java have a unit type ('a type with only one value'). Well, yes, it does: Void.
 * It is though somewhat unfortunate that the only one value it has is 'null', which fails to interact well with
 * @NonNullByDefault.
 * So, here's another Java unit type, which (assuming @NonNullByDefault) has only one value, Unit.UNIT.
 */
@SuppressWarnings( { "Singleton", "InstantiationOfUtilityClass" } ) public final class Unit {
    public static final Unit UNIT = new Unit();

    private Unit() {}
}
