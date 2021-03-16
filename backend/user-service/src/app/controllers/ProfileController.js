import User from '../models/User';

export default {
  async show(req, res) {
    const { id } = req.user;

    const user = await User.findById(id);

    Object.assign(user, { password: undefined });

    return res.json(user);
  },
};
