import mongoose from 'mongoose';

const ExercicioSchema = new mongoose.Schema({
  name: String,
  group: String,
  author: {
    type: String,
    ref: 'Personal',
    default: 'system',
  },
});

export default mongoose.model('Exercicio', ExercicioSchema);
