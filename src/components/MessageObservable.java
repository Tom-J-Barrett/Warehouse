package components;
import java.util.*;
public class MessageObservable extends Observable 
{
	MessageObservable()
	{
		super();
	}
	void changeData(Object data)
	{
		setChanged();
		notifyObservers(data);
	}
}
