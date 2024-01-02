package br.com.renner.application;

public abstract class UseCase<INPUT> {
    public abstract void execute(INPUT input);
}
