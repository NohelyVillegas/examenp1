CREATE TABLE IF NOT EXISTS categorias_producto (
    id_categoria INT PRIMARY KEY,
    nombre_categoria VARCHAR(100) UNIQUE NOT NULL,
    descripcion TEXT,
    version BIGINT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS productos (
    id_producto INT PRIMARY KEY,
    nombre_producto VARCHAR(255) NOT NULL,
    descripcion TEXT,
    precio_venta DECIMAL(10,2) NOT NULL,
    costo_compra DECIMAL(10,2),
    stock_actual INT NOT NULL,
    estado_producto VARCHAR(20) NOT NULL,
    id_categoria INT NOT NULL,
    version BIGINT NOT NULL DEFAULT 0,
    FOREIGN KEY (id_categoria) REFERENCES categorias_producto(id_categoria)
); 