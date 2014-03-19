package modelInterfaces.base;

/**
 * Created by Jon George on 3/13/14.
 */
public interface TurnTracker {

	public abstract void setCurrentTurn(int currentTurn);

	public abstract int getCurrentTurn();

	public abstract void setStatus(String status);

	public abstract String getStatus();
}
