import mongoose from 'mongoose';
import ExecucaoExercicio from './ExecucaoExercicio';

const TreinoSchema = new mongoose.Schema({
  personal: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'Personal',
    required: true,
  },
  aluno: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'Aluno',
  },
  objective: {
    type: String,
    enum: ['Hipertrofia', 'Emagrecer', 'Definir'],
  },
  exercise_list: [
    {
      type: mongoose.Schema.Types.ObjectId,
      ref: 'Execucao_Exercicio',
    },
  ],
  created_at: {
    type: Date,
    default: Date.now(),
  },
  updated_at: {
    type: Date,
    default: Date.now(),
  },
});

MetaTreinoSchema.post(
  'findOneAndRemove',
  async function removeExecutionExercise(doc) {
    const id_executionExercise = doc.exercise_list;

    await ExecucaoExercicio.deleteMany({ _id: id_executionExercise });
  },
);

MetaTreinoSchema.pre(
  'findOneAndUpdate',
  async function updateExecutionExercise(next) {
    const { exercise_list } = this._update;

    exercise_list.map(async exercise => {
      const { _id, ...body } = exercise;
      const res = await ExecucaoExercicio.updateOne({ _id }, body);
      return res;
    });

    next();
  },
);

export default mongoose.model('Treino', TreinoSchema);
