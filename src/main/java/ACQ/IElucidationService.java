package ACQ;

public interface IElucidationService {

	boolean createElucidation(IElucidation elucidation);

	boolean addCaseworkers(long id, IUser ... users);

	boolean removeCaseworkers(long id, IUser ... users);

	IElucidation getElucidation(long id);
}