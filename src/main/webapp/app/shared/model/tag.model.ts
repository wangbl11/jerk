export interface ITag {
  id?: number;
  name?: string;
  type?: number;
  status?: number;
  weight?: number;
  createdDate?: number;
  modifiedDate?: number;
}

export const defaultValue: Readonly<ITag> = {};
