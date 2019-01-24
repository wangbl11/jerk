import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './preference.reducer';
import { IPreference } from 'app/shared/model/preference.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPreferenceProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IPreferenceState {
  search: string;
}

export class Preference extends React.Component<IPreferenceProps, IPreferenceState> {
  state: IPreferenceState = {
    search: ''
  };

  componentDidMount() {
    this.props.getEntities();
  }

  search = () => {
    if (this.state.search) {
      this.props.getSearchEntities(this.state.search);
    }
  };

  clear = () => {
    this.setState({ search: '' }, () => {
      this.props.getEntities();
    });
  };

  handleSearch = event => this.setState({ search: event.target.value });

  render() {
    const { preferenceList, match } = this.props;
    return (
      <div>
        <h2 id="preference-heading">
          <Translate contentKey="jerkkApp.preference.home.title">Preferences</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="jerkkApp.preference.home.createLabel">Create new Preference</Translate>
          </Link>
        </h2>
        <Row>
          <Col sm="12">
            <AvForm onSubmit={this.search}>
              <AvGroup>
                <InputGroup>
                  <AvInput
                    type="text"
                    name="search"
                    value={this.state.search}
                    onChange={this.handleSearch}
                    placeholder={translate('jerkkApp.preference.home.search')}
                  />
                  <Button className="input-group-addon">
                    <FontAwesomeIcon icon="search" />
                  </Button>
                  <Button type="reset" className="input-group-addon" onClick={this.clear}>
                    <FontAwesomeIcon icon="trash" />
                  </Button>
                </InputGroup>
              </AvGroup>
            </AvForm>
          </Col>
        </Row>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="jerkkApp.preference.wechat">Wechat</Translate>
                </th>
                <th>
                  <Translate contentKey="jerkkApp.preference.address">Address</Translate>
                </th>
                <th>
                  <Translate contentKey="jerkkApp.preference.imageUrl">Image Url</Translate>
                </th>
                <th>
                  <Translate contentKey="jerkkApp.preference.lang">Lang</Translate>
                </th>
                <th>
                  <Translate contentKey="jerkkApp.preference.createdDate">Created Date</Translate>
                </th>
                <th>
                  <Translate contentKey="jerkkApp.preference.modifiedDate">Modified Date</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {preferenceList.map((preference, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${preference.id}`} color="link" size="sm">
                      {preference.id}
                    </Button>
                  </td>
                  <td>{preference.wechat}</td>
                  <td>{preference.address}</td>
                  <td>{preference.imageUrl}</td>
                  <td>{preference.lang}</td>
                  <td>{preference.createdDate}</td>
                  <td>{preference.modifiedDate}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${preference.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${preference.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${preference.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ preference }: IRootState) => ({
  preferenceList: preference.entities
});

const mapDispatchToProps = {
  getSearchEntities,
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Preference);
