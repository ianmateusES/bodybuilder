import bcryptjs from 'bcryptjs';

export default {
  encryptPassword(password) {
    return bcryptjs.hash(password, 8);
  },
  compare(password, password_hash) {
    return bcryptjs.compare(password, password_hash);
  },
};
