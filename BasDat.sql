CREATE DATABASE IF NOT EXISTS sistema_envios;
USE sistema_envios;

-- 1. Tablas de Catálogos (Sin llaves foráneas iniciales)
CREATE TABLE estatussucursal (
    idEstatus INT PRIMARY KEY AUTO_INCREMENT,
    estatus VARCHAR(50)
);

CREATE TABLE estatusenvio (
    idEstatus INT PRIMARY KEY AUTO_INCREMENT,
    estatus VARCHAR(50)
);

CREATE TABLE rol (
    idRol INT PRIMARY KEY AUTO_INCREMENT,
    rol VARCHAR(100)
);

CREATE TABLE tipounidad (
    idTipoUnidad INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(50)
);

-- 2. Tabla Sucursal
CREATE TABLE sucursal (
    idSucursal INT PRIMARY KEY AUTO_INCREMENT,
    codigo VARCHAR(20),
    nombre VARCHAR(100),
    idEstatus INT,
    calle VARCHAR(150),
    numero VARCHAR(20),
    colonia VARCHAR(100),
    codigoPostal VARCHAR(10),
    ciudad VARCHAR(100),
    estado VARCHAR(100),
    FOREIGN KEY (idEstatus) REFERENCES estatussucursal(idEstatus)
);

-- 3. Tabla Cliente
CREATE TABLE cliente (
    idCliente INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100),
    apellidoPaterno VARCHAR(100),
    apellidoMaterno VARCHAR(100),
    calle VARCHAR(150),
    numero VARCHAR(20),
    colonia VARCHAR(100),
    codigoPostal VARCHAR(10),
    ciudad VARCHAR(100),
    estado VARCHAR(100),
    telefono VARCHAR(20),
    correo VARCHAR(120)
);

-- 4. Tabla Colaborador
CREATE TABLE colaborador (
    idColaborador INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100),
    apellidoPaterno VARCHAR(100),
    apellidoMaterno VARCHAR(100),
    curp VARCHAR(18),
    correo VARCHAR(120),
    noPersonal VARCHAR(20),
    password VARCHAR(255),
    idRol INT,
    foto LONGBLOB,
    numeroLicencia VARCHAR(50),
    idSucursal INT,
    FOREIGN KEY (idRol) REFERENCES rol(idRol),
    FOREIGN KEY (idSucursal) REFERENCES sucursal(idSucursal)
);

-- 5. Tabla Unidad (Vehículos)
CREATE TABLE unidad (
    idUnidad INT PRIMARY KEY AUTO_INCREMENT,
    marca VARCHAR(100),
    modelo VARCHAR(100),
    anio INT,
    vin VARCHAR(50),
    idTipoUnidad INT,
    numIdenInter VARCHAR(20),
    estatus ENUM('Disponible', 'En Ruta', 'Mantenimiento', 'Baja'), -- Ajustado según visualización
    motivoBaja VARCHAR(255),
    idConductor INT,
    FOREIGN KEY (idTipoUnidad) REFERENCES tipounidad(idTipoUnidad),
    FOREIGN KEY (idConductor) REFERENCES colaborador(idColaborador)
);

-- 6. Tabla Envío
CREATE TABLE envio (
    idEnvio INT PRIMARY KEY AUTO_INCREMENT,
    numeroGuia VARCHAR(30),
    idCliente INT,
    destiNomb VARCHAR(100),
    destiApellidoPaterno VARCHAR(100),
    destiApellidoMaterno VARCHAR(100),
    idSucursalOrigen INT,
    calle VARCHAR(150),
    numero VARCHAR(20),
    colonia VARCHAR(100),
    codigoPostal VARCHAR(10),
    ciudad VARCHAR(100),
    estado VARCHAR(100),
    costoTotal DOUBLE,
    idEstatus INT,
    idConductor INT,
    motivoEstatus VARCHAR(255),
    FOREIGN KEY (idCliente) REFERENCES cliente(idCliente),
    FOREIGN KEY (idSucursalOrigen) REFERENCES sucursal(idSucursal),
    FOREIGN KEY (idEstatus) REFERENCES estatusenvio(idEstatus),
    FOREIGN KEY (idConductor) REFERENCES colaborador(idColaborador)
);

-- 7. Tabla Paquete
CREATE TABLE paquete (
    idPaquete INT PRIMARY KEY AUTO_INCREMENT,
    idEnvio INT,
    descripcion VARCHAR(255),
    peso DOUBLE,
    alto DOUBLE,
    ancho DOUBLE,
    profundidad DOUBLE,
    FOREIGN KEY (idEnvio) REFERENCES envio(idEnvio)
);

-- 8. Historial de Estatus del Envío
CREATE TABLE historialestatusenvio (
    idHistorial INT PRIMARY KEY AUTO_INCREMENT,
    idEnvio INT,
    idEstatus INT,
    FOREIGN KEY (idEnvio) REFERENCES envio(idEnvio),
    FOREIGN KEY (idEstatus) REFERENCES estatusenvio(idEstatus)
);