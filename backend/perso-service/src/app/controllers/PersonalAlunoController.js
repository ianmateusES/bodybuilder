import PersonalAluno from '../models/PersonalAluno';
import User from '../models/User';

export default {
  async index(req, res) {
    const { id } = req.perso;
    const { status: queryStatus } = req.query;

    const query = {
      personal: id,
    };

    if (queryStatus !== undefined) {
      Object.assign(query, { status: queryStatus });
    }

    const personalAlunos = await PersonalAluno.find(query).populate({
      path: 'aluno',
      select: ['name', 'birthday'],
    });

    const alunos = personalAlunos.map(personalAluno => {
      const { aluno: queryAluno, status } = personalAluno;
      const aluno = { status, ...queryAluno._doc };
      return aluno;
    });

    return res.json(alunos);
  },

  async store(req, res) {
    const { id } = req.perso;
    const { email } = req.body;

    const checkUser = await User.findOne({ email });
    if (checkUser) {
      return res.status(400).json({ error: 'Email já cadastrado' });
    }

    const user = await User.create(req.body);
    if (!user) {
      return res.status(400).json({ error: 'Aluno não criado' });
    }

    const personalAluno = await PersonalAluno.create({
      personal: id,
      aluno: user._id,
    });
    if (!personalAluno) {
      await User.deleteOne(user._id);
      return res.status(400).json({ error: 'Error no cadastro do aluno' });
    }

    Object.assign(user, { password: undefined });

    return res.json(user);
  },

  async destroy(req, res) {
    const { id } = req.perso;
    const { id: id_exercicio } = req.params;

    const treino = await Exercicio.findOneAndRemove({
      _id: id_exercicio,
      author: id,
    });

    if (!treino) {
      return res.status(400).json({ error: 'Exercicio não encontrado' });
    }

    return res.status(200).send();
  },
};
