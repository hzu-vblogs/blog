package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * 该接口不能被springboot扫描到，故不能与启动类同级
 * @param <T>
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

    List<T> page(T entity);

    Integer totalCount(T entity);
}
