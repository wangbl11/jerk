import { IRegistration } from 'app/shared/model//registration.model';
import { IPreference } from 'app/shared/model//preference.model';

export const enum AuthStatusEnum {
  A0 = 'A0',
  A1 = 'A1',
  A2 = 'A2'
}

export interface IJerk {
  id?: number;
  username?: string;
  passwd?: string;
  displayname?: string;
  authStatus?: AuthStatusEnum;
  createdDate?: number;
  modifiedDate?: number;
  jerkInfo?: IRegistration;
  preference?: IPreference;
}

export const defaultValue: Readonly<IJerk> = {};
