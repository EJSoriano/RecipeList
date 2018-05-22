package com.RecipeList.FirstSpring.exception;

public class ItemDoesNotExistException extends RuntimeException {

    public ItemDoesNotExistException(String type, Long id) {
        super(String.format("The %s with id %d does not exist.", type, id));
    }

}
