import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IRegistration } from 'app/shared/model/registration.model';
import { getEntities as getRegistrations } from 'app/entities/registration/registration.reducer';
import { IPreference } from 'app/shared/model/preference.model';
import { getEntities as getPreferences } from 'app/entities/preference/preference.reducer';
import { getEntity, updateEntity, createEntity, reset } from './jerk.reducer';
import { IJerk } from 'app/shared/model/jerk.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IJerkUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IJerkUpdateState {
  isNew: boolean;
  jerkInfoId: string;
  preferenceId: string;
}

export class JerkUpdate extends React.Component<IJerkUpdateProps, IJerkUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      jerkInfoId: '0',
      preferenceId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getRegistrations();
    this.props.getPreferences();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { jerkEntity } = this.props;
      const entity = {
        ...jerkEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/jerk');
  };

  render() {
    const { jerkEntity, registrations, preferences, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jerkkApp.jerk.home.createOrEditLabel">
              <Translate contentKey="jerkkApp.jerk.home.createOrEditLabel">Create or edit a Jerk</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : jerkEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="jerk-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="usernameLabel" for="username">
                    <Translate contentKey="jerkkApp.jerk.username">Username</Translate>
                  </Label>
                  <AvField
                    id="jerk-username"
                    type="text"
                    name="username"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="passwdLabel" for="passwd">
                    <Translate contentKey="jerkkApp.jerk.passwd">Passwd</Translate>
                  </Label>
                  <AvField
                    id="jerk-passwd"
                    type="text"
                    name="passwd"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="displaynameLabel" for="displayname">
                    <Translate contentKey="jerkkApp.jerk.displayname">Displayname</Translate>
                  </Label>
                  <AvField
                    id="jerk-displayname"
                    type="text"
                    name="displayname"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="authStatusLabel">
                    <Translate contentKey="jerkkApp.jerk.authStatus">Auth Status</Translate>
                  </Label>
                  <AvInput
                    id="jerk-authStatus"
                    type="select"
                    className="form-control"
                    name="authStatus"
                    value={(!isNew && jerkEntity.authStatus) || 'A0'}
                  >
                    <option value="A0">
                      <Translate contentKey="jerkkApp.AuthStatusEnum.A0" />
                    </option>
                    <option value="A1">
                      <Translate contentKey="jerkkApp.AuthStatusEnum.A1" />
                    </option>
                    <option value="A2">
                      <Translate contentKey="jerkkApp.AuthStatusEnum.A2" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="createdDateLabel" for="createdDate">
                    <Translate contentKey="jerkkApp.jerk.createdDate">Created Date</Translate>
                  </Label>
                  <AvField
                    id="jerk-createdDate"
                    type="string"
                    className="form-control"
                    name="createdDate"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="modifiedDateLabel" for="modifiedDate">
                    <Translate contentKey="jerkkApp.jerk.modifiedDate">Modified Date</Translate>
                  </Label>
                  <AvField id="jerk-modifiedDate" type="string" className="form-control" name="modifiedDate" />
                </AvGroup>
                <AvGroup>
                  <Label for="jerkInfo.id">
                    <Translate contentKey="jerkkApp.jerk.jerkInfo">Jerk Info</Translate>
                  </Label>
                  <AvInput id="jerk-jerkInfo" type="select" className="form-control" name="jerkInfo.id">
                    <option value="" key="0" />
                    {registrations
                      ? registrations.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="preference.id">
                    <Translate contentKey="jerkkApp.jerk.preference">Preference</Translate>
                  </Label>
                  <AvInput id="jerk-preference" type="select" className="form-control" name="preference.id">
                    <option value="" key="0" />
                    {preferences
                      ? preferences.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/jerk" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  registrations: storeState.registration.entities,
  preferences: storeState.preference.entities,
  jerkEntity: storeState.jerk.entity,
  loading: storeState.jerk.loading,
  updating: storeState.jerk.updating,
  updateSuccess: storeState.jerk.updateSuccess
});

const mapDispatchToProps = {
  getRegistrations,
  getPreferences,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(JerkUpdate);
