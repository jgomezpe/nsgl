package nsgl.json;

import nsgl.object.Configurable;
import nsgl.stringify.Stringifyable;

public interface Castable extends Stringifyable, Configurable{
    JSON json();
    @Override
    default String stringify() { return json().stringify(); }
}