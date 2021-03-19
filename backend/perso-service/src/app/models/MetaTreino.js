import mongoose from 'mongoose';
import Treino from './Treino';

const MetaTreinoSchema = new mongoose.Schema({
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
  treinos: [
    {
      type: mongoose.Schema.Types.ObjectId,
      ref: 'Treino',
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

MetaTreinoSchema.post('findOneAndRemove', async function removeTreinos(doc) {
  const id_treinos = doc.treinos;

  await Treino.deleteMany({ _id: id_treinos });
});

MetaTreinoSchema.pre('findOneAndUpdate', async function updateTreino(next) {
  const { treinos } = this._update;

  treinos.map(async treino => {
    const { _id, ...body } = treino;
    const res = await Treino.updateOne({ _id }, body);
    return res;
  });

  next();
});

export default mongoose.model('MetaTreino', MetaTreinoSchema);
