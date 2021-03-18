import { sign } from 'jsonwebtoken';
import Personal from '../models/Personal';
import authConfig from '../../config/auth';

export default {
  async store(req, res) {
    const { email, password } = req.body;

    const personal = await Personal.findOne({ email });

    if (!personal) {
      return res.status(401).json({ error: 'Email/Senha incorreta' });
    }
    if (!(await personal.comparePassword(password))) {
      return res.status(401).json({ error: 'Email/Senha incorreta' });
    }

    const { secret, expiresIn } = authConfig.jwt;

    const token = sign({}, secret, {
      subject: String(personal._id),
      expiresIn,
    });

    Object.assign(personal, { password: undefined });

    return res.json({
      personal,
      token,
    });
  },
};
