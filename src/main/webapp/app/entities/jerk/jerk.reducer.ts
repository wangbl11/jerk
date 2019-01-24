import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IJerk, defaultValue } from 'app/shared/model/jerk.model';

export const ACTION_TYPES = {
  SEARCH_JERKS: 'jerk/SEARCH_JERKS',
  FETCH_JERK_LIST: 'jerk/FETCH_JERK_LIST',
  FETCH_JERK: 'jerk/FETCH_JERK',
  CREATE_JERK: 'jerk/CREATE_JERK',
  UPDATE_JERK: 'jerk/UPDATE_JERK',
  DELETE_JERK: 'jerk/DELETE_JERK',
  RESET: 'jerk/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IJerk>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type JerkState = Readonly<typeof initialState>;

// Reducer

export default (state: JerkState = initialState, action): JerkState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_JERKS):
    case REQUEST(ACTION_TYPES.FETCH_JERK_LIST):
    case REQUEST(ACTION_TYPES.FETCH_JERK):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_JERK):
    case REQUEST(ACTION_TYPES.UPDATE_JERK):
    case REQUEST(ACTION_TYPES.DELETE_JERK):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_JERKS):
    case FAILURE(ACTION_TYPES.FETCH_JERK_LIST):
    case FAILURE(ACTION_TYPES.FETCH_JERK):
    case FAILURE(ACTION_TYPES.CREATE_JERK):
    case FAILURE(ACTION_TYPES.UPDATE_JERK):
    case FAILURE(ACTION_TYPES.DELETE_JERK):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_JERKS):
    case SUCCESS(ACTION_TYPES.FETCH_JERK_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_JERK):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_JERK):
    case SUCCESS(ACTION_TYPES.UPDATE_JERK):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_JERK):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/jerks';
const apiSearchUrl = 'api/_search/jerks';

// Actions

export const getSearchEntities: ICrudSearchAction<IJerk> = (query, page, size, sort) => ({
  type: ACTION_TYPES.SEARCH_JERKS,
  payload: axios.get<IJerk>(`${apiSearchUrl}?query=${query}${sort ? `&page=${page}&size=${size}&sort=${sort}` : ''}`)
});

export const getEntities: ICrudGetAllAction<IJerk> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_JERK_LIST,
    payload: axios.get<IJerk>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IJerk> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_JERK,
    payload: axios.get<IJerk>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IJerk> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_JERK,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IJerk> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_JERK,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IJerk> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_JERK,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
