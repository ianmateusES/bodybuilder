import { verify } from 'jsonwebtoken';
import authConfig from '../../config/auth';

export default (req, res, next) => {
  const authHeader = req.headers.authorization;
  if (!authHeader) {
    return res.status(401).json({ error: 'JWT token is missing' });
  }

  const [, token] = authHeader.split(' ');

  try {
    const decoded = verify(token, authConfig.jwt.secret);

    const { sub } = decoded;

    req.perso = {
      id: sub,
    };

    return next();
  } catch (err) {
    return res.status(401).json({ error: 'Invalid JWT token' });
  }
};
