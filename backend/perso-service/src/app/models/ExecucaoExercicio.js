import mongoose from 'mongoose';

const ExecucaoExercicioSchema = new mongoose.Schema({
  division: {
    type: String,
    enum: ['A', 'B', 'C', 'D', 'E', 'F', 'G'],
    required: true,
  },
  exercise: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'Exercicio',
    required: true,
  },
  methodology: {
    type: String,
    enum: ['Drop set', 'Biset', 'Fst-7', 'Descanso progressivo', 'Rest pause'],
    required: true,
  },
  series: Number,
  repetitions: Number,
  comments: String,
  created_at: {
    type: Date,
    default: Date.now(),
  },
  updated_at: {
    type: Date,
    default: Date.now(),
  },
});

export default mongoose.model('Execucao_Exercicio', ExecucaoExercicioSchema);
