export const enum Decision {
  YES = 'YES',
  NO = 'NO'
}

export const enum HxjslyEnum {
  S1 = 'S1',
  S2 = 'S2',
  S3 = 'S3',
  S4 = 'S4',
  OTHER = 'OTHER'
}

export const enum KjcgzhEnum {
  S1 = 'S1',
  S2 = 'S2',
  S3 = 'S3',
  OTHER = 'OTHER'
}

export const enum JmlyqkEnum {
  K1 = 'K1',
  K2 = 'K2',
  K3 = 'K3'
}

export const enum XbEnum {
  MAN = 'MAN',
  WOMAN = 'WOMAN'
}

export const enum AgeEnum {
  A1 = 'A1',
  A2 = 'A2',
  A3 = 'A3',
  A4 = 'A4',
  A5 = 'A5',
  A6 = 'A6',
  A7 = 'A7'
}

export const enum RzjhgkfwEnum {
  O1 = 'O1',
  O2 = 'O2',
  O3 = 'O3'
}

export const enum RzmbEnum {
  R1 = 'R1',
  R2 = 'R2',
  R3 = 'R3',
  R4 = 'R4',
  R5 = 'R5',
  R6 = 'R6',
  R7 = 'R7',
  R8 = 'R8'
}

export interface IRegistration {
  id?: number;
  registType?: number;
  dwqc?: string;
  hxcpmc?: string;
  zztjdw?: string;
  dwhgrdz?: string;
  szqylx?: string;
  ssly?: string;
  gscpjj?: string;
  mbkhsc?: string;
  dqzykh?: string;
  gnwhjjx?: string;
  zljs?: Decision;
  hxjsly?: HxjslyEnum;
  kjcgzh?: KjcgzhEnum;
  jmlyqk?: JmlyqkEnum;
  jscsd?: string;
  jzmsylqk?: string;
  jzysjs?: string;
  fzrdh?: string;
  xb?: XbEnum;
  lxfs?: string;
  email?: string;
  fzrnl?: AgeEnum;
  tdpjnl?: AgeEnum;
  gjrcs?: number;
  sfgjrzgxjsqy?: Decision;
  tdysjs?: string;
  xycz?: string;
  wlxwhdzclx?: string;
  wlxwhdzclx1?: string;
  sfxyxc?: string;
  rzjhgkfw?: RzjhgkfwEnum;
  rzmb?: RzmbEnum;
  lxrzw?: string;
  lxdh?: string;
  lxyx?: string;
  lxdz?: string;
  ssly1?: string;
  createdDate?: number;
  modifiedDate?: number;
  fbzt?: number;
}

export const defaultValue: Readonly<IRegistration> = { fbzt: 0 };
