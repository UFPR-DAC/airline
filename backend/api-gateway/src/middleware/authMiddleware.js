import jwt from 'jsonwebtoken';
import {SERVICE_CONFIG} from "../config/services.js";

// IMPORTANTE: Esta chave secreta é apenas um exemplo.
// Em um ambiente de produção, use variáveis de ambiente!
const JWT_SECRET = 'seu-jwt-secret-super-secreto';

export async function autenticarToken(req, res, next) {
    const token = extrairToken(req);
    if (!token) return res.status(401).json({ error: "Token inválido" });

    try {
        const response = await axios.post(`${SERVICE_CONFIG.AUTH.url}/validar`,
            {},
            {
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                }
            })
        if (response.data.authenticated) {
            const decoded = jwt.decode(token);
            response.data.user = decoded;
            return next();
        } else {
            return res.status(403).json({ error: "Token inválido" });
        }
    } catch (e) {
        console.error("Erro ao validar token.");
        if (axios.isAxiosError(error) && (error.response?.status === 403 || error.response?.status === 401)) {
            return res.status(error.response?.status).json({ error: "Token rejeitado." });
        }
        return res.status(500).json({ error: "Erro interno ao autenticar token."});
    }
}

export function extrairToken(req) {
    const authHeader = req.headers['authorization'];
    if (!authHeader) return null;
    const parts = authHeader.split(' ');
    if (parts.length !== 2 || parts[0] !== 'Bearer') return null;
    return parts[1];
}

export function autorizarUsuario(...tiposUsuario) {
    return (req, res, next) => {
        const token = extrairToken(req);
        if (!token) return res.status(401).json({ error: "Requisição sem token." });

        const decoded = jwt.decode(token);
        if (!decoded) return res.sendStatus(401);

        if (tiposUsuario.includes(decoded.tipo)) {
            next();
        } else {
            req.sendStatus(403);
        }
    }
}
