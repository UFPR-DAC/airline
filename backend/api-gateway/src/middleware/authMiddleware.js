const jwt = require('jsonwebtoken');

// IMPORTANTE: Esta chave secreta é apenas um exemplo.
// Em um ambiente de produção, use variáveis de ambiente!
const JWT_SECRET = 'seu-jwt-secret-super-secreto';

const autenticarEAutorizar = (tiposPermitidos) => {
    return (req, res, next) => {
        const authHeader = req.headers['authorization'];
        const token = authHeader && authHeader.split(' ')[1]; // Formato "Bearer TOKEN"

        if (!token) {
            // Conforme a especificação, retorna 401 se não houver token
            return res.status(401).json({ error: 'Nenhum token fornecido.' });
        }

        jwt.verify(token, JWT_SECRET, (err, decoded) => {
            if (err) {
                // Token inválido ou expirado
                return res.status(401).json({ error: 'Token inválido ou expirado.' });
            }

            // O token é válido, agora verificamos a autorização (permissão de acesso)
            // A API de login retorna um campo "tipo" com o perfil do usuário
            const tipoUsuario = decoded.tipo; 

            if (tiposPermitidos.includes(tipoUsuario)) {
                req.user = decoded; // Adiciona os dados do usuário decodificados à requisição
                next(); // Permissão concedida, continua para o microsserviço
            } else {
                // O usuário não tem o tipo/perfil necessário para acessar este recurso
                // Conforme a especificação, retorna 403 Forbidden
                return res.status(403).json({ error: 'Acesso negado. Permissão insuficiente.' });
            }
        });
    };
};

module.exports = autenticarEAutorizar;