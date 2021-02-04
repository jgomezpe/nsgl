package nsgl.search;

import nsgl.real.Abs;

public abstract class RealQualityGoal<T> extends DistanceToGoal<T,Double> {
    public RealQualityGoal( double goal_value ){ super( goal_value, new Abs()); }
}