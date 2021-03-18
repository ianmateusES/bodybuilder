import Exercicio from '../models/Exercicio';

export default {
  async index(req, res) {
    const { id } = req.perso;

    const exercicios = await Exercicio.find({
      $or: [{ author: id }, { author: 'system' }],
    });

    return res.json(exercicios);
  },

  async store(req, res) {
    const { id } = req.perso;

    Object.assign(req.body, { author: id });

    const exercicio = await Exercicio.create(req.body);

    return res.json(exercicio);
  },
};
