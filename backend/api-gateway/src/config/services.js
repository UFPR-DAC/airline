export const SERVICE_CONFIG = {
    AUTH: {
        url: process.env.AUTH_SERVICE_URL || 'http://localhost:8085'
    },
    CLIENTE: {
        url: process.env.CLIENTE_SERVICE_URL || 'http://localhost:8080/clientes'
    },
    FLIGHTS: {
        url: process.env.FLIGHT_SERVICE_URL || 'http://localhost:8083/voos'
    },
    FUNCIONARIO: {
        url: process.env.FUNCIONARIO_SERVICE_URL || 'http://localhost:8081/funcionarios'
    },
    RESERVATION: {
        url: process.env.RESERVATION_SERVICE_URL || 'http://localhost:8084/api/reservas'
    },
    RESERVATION_VIEW: {
        url: `${process.env.RESERVATION_SERVICE_URL || 'http://localhost:8084/api/reservas'}/view`
    }
};