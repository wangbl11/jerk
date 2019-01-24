import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './setting.reducer';
import { ISetting } from 'app/shared/model/setting.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISettingUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ISettingUpdateState {
  isNew: boolean;
}

export class SettingUpdate extends React.Component<ISettingUpdateProps, ISettingUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
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
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { settingEntity } = this.props;
      const entity = {
        ...settingEntity,
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
    this.props.history.push('/entity/setting');
  };

  render() {
    const { settingEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jerkkApp.setting.home.createOrEditLabel">
              <Translate contentKey="jerkkApp.setting.home.createOrEditLabel">Create or edit a Setting</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : settingEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="setting-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="nameLabel" for="name">
                    <Translate contentKey="jerkkApp.setting.name">Name</Translate>
                  </Label>
                  <AvField
                    id="setting-name"
                    type="text"
                    name="name"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="typeLabel">
                    <Translate contentKey="jerkkApp.setting.type">Type</Translate>
                  </Label>
                  <AvInput
                    id="setting-type"
                    type="select"
                    className="form-control"
                    name="type"
                    value={(!isNew && settingEntity.type) || 'STRING'}
                  >
                    <option value="STRING">
                      <Translate contentKey="jerkkApp.SettingTypeEnum.STRING" />
                    </option>
                    <option value="INTEGER">
                      <Translate contentKey="jerkkApp.SettingTypeEnum.INTEGER" />
                    </option>
                    <option value="BOOL">
                      <Translate contentKey="jerkkApp.SettingTypeEnum.BOOL" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="valueLabel" for="value">
                    <Translate contentKey="jerkkApp.setting.value">Value</Translate>
                  </Label>
                  <AvField
                    id="setting-value"
                    type="text"
                    name="value"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 200, errorMessage: translate('entity.validation.maxlength', { max: 200 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="defvalueLabel" for="defvalue">
                    <Translate contentKey="jerkkApp.setting.defvalue">Defvalue</Translate>
                  </Label>
                  <AvField
                    id="setting-defvalue"
                    type="text"
                    name="defvalue"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 200, errorMessage: translate('entity.validation.maxlength', { max: 200 }) }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/setting" replace color="info">
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
  settingEntity: storeState.setting.entity,
  loading: storeState.setting.loading,
  updating: storeState.setting.updating,
  updateSuccess: storeState.setting.updateSuccess
});

const mapDispatchToProps = {
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
)(SettingUpdate);
