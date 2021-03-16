import { sign } from 'jsonwebtoken';

import User from '../models/User';
import authConfig from '../../config/auth';

export default {
  async store(req, res) {
    const { email, password } = req.body;

    const user = await User.findOne({ email });

    if (!user) {
      return res.status(401).json({ error: 'Email/Senha incorreta' });
    }
    if (!(await user.comparePassword(password))) {
      return res.status(401).json({ error: 'Email/Senha incorreta' });
    }

    const { secret, expiresIn } = authConfig.jwt;

    const token = sign({}, secret, {
      subject: String(user._id),
      expiresIn,
    });

    Object.assign(user, { password: undefined });

    return res.json({
      user,
      token,
    });
  },
};
