import mongoose from 'mongoose';
import passwordEncrypt from '../providers/passwordEncrypt';

const UserSchema = new mongoose.Schema({
  name: String,
  email: {
    type: String,
    trim: true,
    unique: true,
    required: 'Email required',
  },
  password: {
    type: String,
    required: 'Password required',
  },
  birthday: {
    type: Date,
    required: true,
  },
  telephone: String,
});

UserSchema.pre('save', async function save(next) {
  const user = this;
  user.password = await passwordEncrypt.encryptPassword(user.password);
  next();
});

UserSchema.methods = {
  comparePassword(candidatePassword) {
    return passwordEncrypt.compare(candidatePassword, this.password);
  },
};

export default mongoose.model('Aluno', UserSchema);
