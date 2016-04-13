package vital;

/**
 * Generic Node class.
 * @author Kile_DaSilva
 *
 * @param <T> Class contained within Node
 */
public class Node<T>
{
	/**
	 * Reference to the next node within the linked list
	 */
	private Node next;
	
	/**
	 * Reference to the item held by the Node
	 */
	private T item;
	
	/**
	 * Get the next node
	 */
	public Node getNext()
	{
		return next;
	}
	
	/**
	 * Get the item held by this Node
	 */
	public T getItem()
	{
		return item;
	}
	
	/**
	 * Set the next Node
	 */
	public void setNext(Node next)
	{
		this.next = next;
	}
	
	/**
	 * Set the item of the Node
	 */
	public void setItem(T item)
	{
		this.item = item;
	}
}
