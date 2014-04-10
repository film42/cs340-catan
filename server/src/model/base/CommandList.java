package model.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import comm.moves.base.Command;

import model.JsonImpl;

public class CommandList extends JsonImpl implements Iterable {

	ArrayList<Command> commandList;

	public CommandList() {
		commandList = new ArrayList<Command>();
	}

	public CommandList(List<Command> oldList) {
		commandList = new ArrayList<Command>(oldList);
	}

	public int size() {
		return commandList.size();
	}

	public boolean isEmpty() {
		return commandList.isEmpty();
	}

	public boolean contains(Command o) {
		return commandList.contains(o);
	}

	public Iterator<Command> iterator() {
		return commandList.iterator();
	}

	public Command[] toArray() {
		return (Command[]) commandList.toArray();
	}

	public Command[] toArray(Command[] a) {
		return commandList.toArray(a);
	}

	public boolean add(Command e) {
		return commandList.add((Command) e);
	}

	public boolean remove(Command o) {
		return commandList.remove(o);
	}

	public boolean containsAll(Collection<?> c) {
		return commandList.containsAll(c);
	}

	public boolean addAll(Collection<Command> c) {
		return commandList.addAll(c);
	}

	public boolean addAll(int index, Collection<Command> c) {
		return commandList.addAll(index, c);
	}

	public boolean removeAll(Collection<?> c) {
		return commandList.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return commandList.retainAll(c);
	}


	public void clear() {
		commandList.clear();
	}

	public Command get(int index) {
		return commandList.get(index);
	}

	public Command set(int index, Command element) {
		return commandList.set(index, (Command) element);
	}

	public void add(int index, Command element) {
		commandList.add(index, (Command) element);
	}

	public Command remove(int index) {
		return commandList.remove(index);
	}

	public int indexOf(Command o) {
		return commandList.indexOf(o);
	}

	public int lastIndexOf(Command o) {
		return commandList.lastIndexOf(o);
	}

	public ListIterator<Command> listIterator() {
		return commandList.listIterator();
	}

	public ListIterator<Command> listIterator(int index) {
		return commandList.listIterator(index);
	}

	public List<Command> subList(int fromIndex, int toIndex) {
		return commandList.subList(fromIndex, toIndex);
	}

}
