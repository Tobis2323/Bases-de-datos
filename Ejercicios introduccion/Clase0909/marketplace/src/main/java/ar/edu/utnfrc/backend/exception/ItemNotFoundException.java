package ar.edu.utnfrc.backend.exception;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String sku) {
        super("SKU inexistente en catálogo: " + sku);
    }
}
