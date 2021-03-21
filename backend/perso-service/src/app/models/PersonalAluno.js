import mongoose from 'mongoose';

const PersonalAlunoSchema = new mongoose.Schema({
  personal: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'Personal',
  },
  aluno: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'Aluno',
  },
  status: {
    type: Boolean,
    default: true,
  },
});

export default mongoose.model('Personal_aluno', PersonalAlunoSchema);
