import Personal from '../models/Personal';

export default {
  async store(req, res) {
    const { email } = req.body;

    let personal = await Personal.findOne({ email });
    if (personal) {
      return res.status(400).json({ error: 'Email já cadastrado' });
    }

    personal = await Personal.create(req.body);

    Object.assign(personal, { password: undefined });

    return res.json(personal);
  },
};
