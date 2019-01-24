import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './preference.reducer';
import { IPreference } from 'app/shared/model/preference.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPreferenceUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPreferenceUpdateState {
  isNew: boolean;
}

export class PreferenceUpdate extends React.Component<IPreferenceUpdateProps, IPreferenceUpdateState> {
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
      const { preferenceEntity } = this.props;
      const entity = {
        ...preferenceEntity,
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
    this.props.history.push('/entity/preference');
  };

  render() {
    const { preferenceEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jerkkApp.preference.home.createOrEditLabel">
              <Translate contentKey="jerkkApp.preference.home.createOrEditLabel">Create or edit a Preference</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : preferenceEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="preference-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="wechatLabel" for="wechat">
                    <Translate contentKey="jerkkApp.preference.wechat">Wechat</Translate>
                  </Label>
                  <AvField
                    id="preference-wechat"
                    type="text"
                    name="wechat"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="addressLabel" for="address">
                    <Translate contentKey="jerkkApp.preference.address">Address</Translate>
                  </Label>
                  <AvField id="preference-address" type="text" name="address" />
                </AvGroup>
                <AvGroup>
                  <Label id="imageUrlLabel" for="imageUrl">
                    <Translate contentKey="jerkkApp.preference.imageUrl">Image Url</Translate>
                  </Label>
                  <AvField id="preference-imageUrl" type="text" name="imageUrl" />
                </AvGroup>
                <AvGroup>
                  <Label id="langLabel" for="lang">
                    <Translate contentKey="jerkkApp.preference.lang">Lang</Translate>
                  </Label>
                  <AvField id="preference-lang" type="text" name="lang" />
                </AvGroup>
                <AvGroup>
                  <Label id="createdDateLabel" for="createdDate">
                    <Translate contentKey="jerkkApp.preference.createdDate">Created Date</Translate>
                  </Label>
                  <AvField
                    id="preference-createdDate"
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
                    <Translate contentKey="jerkkApp.preference.modifiedDate">Modified Date</Translate>
                  </Label>
                  <AvField id="preference-modifiedDate" type="string" className="form-control" name="modifiedDate" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/preference" replace color="info">
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
  preferenceEntity: storeState.preference.entity,
  loading: storeState.preference.loading,
  updating: storeState.preference.updating,
  updateSuccess: storeState.preference.updateSuccess
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
)(PreferenceUpdate);
