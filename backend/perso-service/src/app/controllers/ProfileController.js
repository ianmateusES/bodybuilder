import Personal from '../models/Personal';

export default {
  async show(req, res) {
    const { id } = req.perso;

    const personal = await Personal.findById(id);

    Object.assign(personal, { password: undefined });

    return res.json(personal);
  },

  async update(req, res) {
    const { id } = req.perso;
    const { email, oldPassword } = req.body;

    let personal = await Personal.findById(id);

    if (email && email !== personal.email) {
      const personalExist = await Personal.findOne({ email });
      if (personalExist) {
        return res.status(400).json({ error: 'Email já existente' });
      }
    }

    if (oldPassword && !(await personal.comparePassword(oldPassword))) {
      return res.status(401).json({ error: 'Senhas atual não combina' });
    }

    personal = await Personal.findOneAndUpdate({ _id: id }, req.body, {
      new: true,
    });

    Object.assign(personal, { password: undefined });

    return res.json(personal);
  },
};
