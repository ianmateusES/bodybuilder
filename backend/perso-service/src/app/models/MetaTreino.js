import mongoose from 'mongoose';

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

export default mongoose.model('MetaTreino', MetaTreinoSchema);
