import jwt from 'jsonwebtoken';

// IMPORTANTE: Esta chave secreta é apenas um exemplo.
// Em um ambiente de produção, use variáveis de ambiente!
const JWT_SECRET = 'seu-jwt-secret-super-secreto';

export default function autenticarEAutorizar(tiposPermitidos) {
    return (req, res, next) => {
        const authHeader = req.headers['authorization'];
        const token = authHeader && authHeader.split(' ')[1]; // Formato "Bearer TOKEN"

        if (!token) {
            return res.status(401).json({ error: 'Nenhum token fornecido.' });
        }

        jwt.verify(token, JWT_SECRET, (err, decoded) => {
            if (err) {
                return res.status(401).json({ error: 'Token inválido ou expirado.' });
            }

            const tipoUsuario = decoded.tipo;

            if (tiposPermitidos.includes(tipoUsuario)) {
                req.user = decoded;
                next();
            } else {
                return res.status(403).json({ error: 'Acesso negado. Permissão insuficiente.' });
            }
        });
    };
}
