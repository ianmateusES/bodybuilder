import User from '../models/User';

export default {
  async store(req, res) {
    const { email } = req.body;

    let user = await User.findOne({ email });
    if (user) {
      return res.status(400).json({ error: 'Email já cadastrado' });
    }

    user = await User.create(req.body);

    Object.assign(user, { password: undefined });

    return res.json(user);
  },
};
