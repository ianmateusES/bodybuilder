import mongoose from 'mongoose';
import passwordEncrypt from '../providers/passwordEncrypt';

const PersonalSchema = new mongoose.Schema({
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
  cref: {
    type: String,
    unique: true,
    required: 'CREF required',
  },
  address: String,
  number: Number,
  city: String,
  uf: String,
  created_at: {
    type: Date,
    default: Date.now(),
  },
  updated_at: {
    type: Date,
    default: Date.now(),
  },
});

PersonalSchema.pre('save', async function save(next) {
  const personal = this;

  personal.password = await passwordEncrypt.encryptPassword(personal.password);
  next();
});

PersonalSchema.pre('findOneAndUpdate', async function update(next) {
  const personalUpdate = this._update;

  if (personalUpdate.old_password) {
    personalUpdate.password = await passwordEncrypt.encryptPassword(
      personalUpdate.password,
    );
  }

  personalUpdate.updated_at = Date.now();

  next();
});

PersonalSchema.methods = {
  comparePassword(candidatePassword) {
    return passwordEncrypt.compare(candidatePassword, this.password);
  },
};

export default mongoose.model('Personal', PersonalSchema);
