package be.pxl.ja.cluedo.commands.interfaces;

@FunctionalInterface
public interface Command<T> {
	void execute(T input);
}
