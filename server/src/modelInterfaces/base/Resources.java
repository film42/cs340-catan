package modelInterfaces.base;

/**
 * Created by Jon George on 3/13/14.
 */
public interface Resources {

	public abstract void setOre(int ore);

	public abstract int getOre();

	public abstract void setWheat(int wheat);

	public abstract int getWheat();

	public abstract void setSheep(int sheep);

	public abstract int getSheep();

	public abstract void setWood(int wood);

	public abstract int getWood();

	public abstract void setBrick(int brick);

	public abstract int getBrick();

    int getResourceCount();
}
