export const enum SettingTypeEnum {
  STRING = 'STRING',
  INTEGER = 'INTEGER',
  BOOL = 'BOOL'
}

export interface ISetting {
  id?: number;
  name?: string;
  type?: SettingTypeEnum;
  value?: string;
  defvalue?: string;
  createdDate?: number;
  modifiedDate?: number;
}

export const defaultValue: Readonly<ISetting> = {};
