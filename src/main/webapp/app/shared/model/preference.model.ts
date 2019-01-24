export interface IPreference {
  id?: number;
  wechat?: string;
  address?: string;
  imageUrl?: string;
  lang?: string;
  createdDate?: number;
  modifiedDate?: number;
}

export const defaultValue: Readonly<IPreference> = {};
