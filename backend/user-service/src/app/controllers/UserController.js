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

  async update(req, res) {
    const { id } = req.user;
    const { email, oldPassword } = req.body;

    let user = await User.findById(id);

    if (email && email !== user.email) {
      const userExist = await User.findOne({ email });
      if (userExist) {
        return res.status(400).json({ error: 'Email já existente' });
      }
    }

    if (oldPassword && !(await user.comparePassword(oldPassword))) {
      return res.status(401).json({ error: 'Senhas atual não combina' });
    }

    user = await User.findByIdAndUpdate({ _id: id }, req.body, { new: true });

    Object.assign(user, { password: undefined });

    return res.json(user);
  },
};
